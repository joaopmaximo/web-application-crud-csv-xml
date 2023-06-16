const formulario = document.querySelector("form");
const Inome = document.querySelector(".nome");
const Iemail = document.querySelector(".email");
const Isenha = document.querySelector(".senha");
const Itelefone = document.querySelector(".telefone");
const lista = document.querySelector(".lista");
const divLista = document.querySelector(".div-lista");
const botaoLista = document.querySelector(".botaoLista");
const fallback = document.createElement("span");
fallback.textContent = "Sem conexão com a API";

function cadastrar() {
    // Fetch é um método para fazer requisições HTTP
    return new Promise ((resolve, reject) => {
        fetch("http://localhost:8080/usuarios", {
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            }, // Definindo o cabeçalho da requisição, para maior controle ao se comunicar com a aplicação
            method: "POST", // Definindo o método da requisição (verbo) para POST, insinuando que queremos enviar os dados do body
            body: JSON.stringify ({
                nome: Inome.value,
                email: Iemail.value,
                senha: Isenha.value,
                telefone: Itelefone.value
            }) // O body seria o conteudo da requisição, nesse caso ele usa o JSON.stringify para converter os dados para JSON
        })
        .then(response => console.log(response))
        .then(response => resolve(response))
        .catch(error => reject(error))
    });
}

function listar() {
    fetch("http://localhost:8080/usuarios")
    .then(response => response.json())
    .then(data => {
        data.forEach(usuario => {
            const itemLista = document.createElement("li"); // Criando uma tag "li"
<<<<<<< HEAD:front/script.js
            itemLista.innerHTML = `${usuario.id} <a href="./usuario.html?id=${usuario.id}">${usuario.nome}</a>`; // innertext coloca texto dentro da tag selecionada
=======
            itemLista.innerText = `${usuario.id} ${usuario.nome}`; // innertext coloca texto dentro da tag selecionada
>>>>>>> 360a38ecdaad3cc21b1bad8566ad7ce6e3d313e9:script.js
            lista.appendChild(itemLista); // O appendchild coloca "itemList" dentro da tag da classe lista, que no caso é uma "ul"

            const iconeLixeira = document.createElement("span"); // Criando a tag "span" que será o icone da lixeira
            iconeLixeira.className = "lixeira";
            iconeLixeira.id = `${usuario.id}`; // Preenche o atributo id com o id do banco de dados para cada icone da lista
            const imgLixeira = document.createElement("img");
            imgLixeira.src = "./images/trash.png";
            imgLixeira.alt = "lixeira";
            iconeLixeira.appendChild(imgLixeira); // Colocando a imagem dentro do span
            itemLista.appendChild(iconeLixeira); // Colocando o icone da lixeira em cada item
        });
        // Evento para que cada icone com classe "lixeira" delete o item pelo seu ID
        const acaoLixeira = document.querySelectorAll(".lixeira");
        acaoLixeira.forEach(item => {
            item.addEventListener('click', async function() {
                const itemDelete = item.id; // Guardando o id do item clicado
                await deletar(itemDelete); // Passando o id como parâmetro para a função delete
                limpar();
                listar();
            });
        });
    })
    .catch(error => {
        console.error(error);
        divLista.appendChild(fallback);
        divLista.insertBefore(fallback, botaoLista);
    });
}

function deletar(id) {
    return new Promise((resolve, reject) => {
        fetch(`http://localhost:8080/usuarios/${id}`, {method: "DELETE"})
        .then(response => resolve(response))
        .catch(error => reject(error));
    });
}

// função para limpar os campos do formulário
function limpar() {
    Inome.value = "";
    Iemail.value = "";
    Isenha.value = "";
    Itelefone.value = "";
    lista.innerHTML = "";
    if (divLista.contains(fallback)) {
        divLista.removeChild(fallback);
    }
}

// Botão de cadastro
formulario.addEventListener ('submit', async function(event) {
    event.preventDefault();
    await cadastrar();
    limpar(); // limpando os campos do formulário após cadastrar
    listar();
});

// Botão de atualizar lista
botaoLista.addEventListener ('click', function() {
    limpar(); // removendo a lista anterior
    listar(); // adicionando a lista atualizada
});

listar();
