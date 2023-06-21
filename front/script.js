const formulario = document.querySelector('form');
const Inome = document.querySelector('.nome');
const Iemail = document.querySelector('.email');
const Isenha = document.querySelector('.senha');
const Itelefone = document.querySelector('.telefone');
const lista = document.querySelector('.lista');
const divLista = document.querySelector('.div-lista');
const botaoLista = document.querySelector('.botaoLista');
const botaoCsv = document.querySelector('.botaoCsv');
const botaoXml = document.querySelector('.botaoXml');
const fallback = document.querySelector('#fallback');
const descricao = document.querySelector('#descricao');

function cadastrar() {
    // Fetch é um método para fazer requisições HTTP
    return new Promise ((resolve, reject) => {
        fetch('http://localhost:8080/usuarios', {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }, // Definindo o cabeçalho da requisição, para maior controle ao se comunicar com a aplicação
            method: 'POST', // Definindo o método da requisição (verbo) para POST, insinuando que queremos enviar os dados do body
            body: JSON.stringify ({
                nome: Inome.value,
                email: Iemail.value,
                senha: Isenha.value,
                telefone: Itelefone.value
            }) // O body seria o conteudo da requisição, nesse caso ele usa o JSON.stringify para converter os dados para JSON
        })
        .then(response => {console.log(response), resolve(response)})
        .catch(error => reject(error))
    })
}

function listar() {
    fetch('http://localhost:8080/usuarios')
    .then(response => response.json())
    .then(data => {
        data.forEach(usuario => {
            const itemLista = document.createElement('li'); // Criando uma tag "li"
            itemLista.innerHTML = `${usuario.id} <a href="./usuario.html?id=${usuario.id}">${usuario.nome}</a>`; // innertext coloca texto dentro da tag selecionada
            lista.appendChild(itemLista); // O appendchild coloca "itemList" dentro da tag da classe lista, que no caso é uma "ul"
            const iconeLixeira = document.createElement('span'); // Criando a tag "span" que será o icone da lixeira
            iconeLixeira.className = 'lixeira';
            iconeLixeira.id = `${usuario.id}`; // Preenche o atributo id com o id do banco de dados para cada icone da lista
            const imgLixeira = document.createElement('img');
            imgLixeira.src = './images/trash.png';
            imgLixeira.alt = 'lixeira';
            iconeLixeira.appendChild(imgLixeira); // Colocando a imagem dentro do span
            itemLista.appendChild(iconeLixeira); // Colocando o icone da lixeira em cada item
        });
        // Evento para que cada icone com classe "lixeira" delete o item pelo seu ID
        const acaoLixeira = document.querySelectorAll('.lixeira');
        acaoLixeira.forEach(item => {
            item.addEventListener('click', async function() {
                const itemDelete = item.id // Guardando o id do item clicado
                await deletar(itemDelete) // Passando o id como parâmetro para a função delete
                limpar()
                listar()
            })
        })
    })
    .catch(error => {
        console.error(error);
        fallback.textContent = 'Sem conexão com a API.';
    });
}

function deletar(id) {
    return new Promise((resolve, reject) => {
        fetch(`http://localhost:8080/usuarios/${id}`, {method: 'DELETE'})
        .then(response => resolve(response))
        .catch(error => reject(error))
    })
}

// função para limpar os campos do formulário
function limpar() {
    Inome.value = '';
    Iemail.value = '';
    Isenha.value = '';
    Itelefone.value = '';
    lista.innerHTML = '';
    fallback.textContent = '';
}

function baixarCsv() {
    fetch('http://localhost:8080/usuarios/csv')
    .then(response => response.blob())
    .then(blob => {
        // Cria um URL temporário para o objeto Blob
        const url = URL.createObjectURL(blob);

        // Cria um link de download programaticamente
        const link = document.createElement('a');
        link.href = url;
        link.download = 'usuarios.csv'; // Define o nome do arquivo
        link.click();

        // Limpa o URL temporário
        URL.revokeObjectURL(url);
    })
    .catch(error => console.error(error));
}

function baixarXml() {
    fetch('http://localhost:8080/usuarios/xml')
    .then(response => response.blob())
    .then(blob => {
        // Cria um URL temporário para o objeto Blob
        const url = URL.createObjectURL(blob);

        // Cria um link de download programaticamente
        const link = document.createElement('a');
        link.href = url;
        link.download = 'usuarios.xml'; // Define o nome do arquivo
        link.click();

        // Limpa o URL temporário
        URL.revokeObjectURL(url);
    })
    .catch(error => console.error(error));
}

// Botão de cadastro
formulario.addEventListener('submit', async event => {
    event.preventDefault()
    try {
        await cadastrar()
    }
    catch {
        descricao.textContent = "Erro ao cadastrar usuário."
    }
    limpar() // limpando os campos do formulário após cadastrar
    listar()
})

// Botão de atualizar lista
botaoLista.addEventListener('click', () => {
    limpar() // removendo a lista anterior
    listar() // adicionando a lista atualizada
})

botaoCsv.addEventListener('click', () => {
    baixarCsv()
})

botaoXml.addEventListener('click', () => {
    baixarXml();
})

listar()
