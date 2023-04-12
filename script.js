const formulario = document.querySelector("form");
const Inome = document.querySelector(".nome");
const Iusuario = document.querySelector(".usuario");
const Iemail = document.querySelector(".email");
const Isenha = document.querySelector(".senha");
const Itelefone = document.querySelector(".telefone");
const lista = document.querySelector(".lista");
const botaoLista = document.querySelector(".botaoLista");


function cadastrar() {
    // Fetch é um método para fazer requisições HTTP
    fetch ("http://localhost:8080/usuarios", {
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        }, // Definindo o cabeçalho da requisição, para maior controle ao se comunicar com a aplicação
        method: "POST", // Definindo o método da requisição (verbo) para POST, insinuando que queremos enviar os dados do body
        body: JSON.stringify ({
            nome_completo: Inome.value,
            username: Iusuario.value,
            email: Iemail.value,
            senha: Isenha.value,
            telefone: Itelefone.value
        }) // O body seria o conteudo da requisição, nesse caso ele usa o JSON.stringify para converter
        // os dados para JSON
    })
        .then(res => console.log(res))
        .catch(res => console.log(res))
}

function listar() {
    fetch("http://localhost:8080/usuarios")
    .then(response => response.json())
    .then(data => {
        data.forEach(usuario => {
        const itemLista = document.createElement("li"); // itemList é uma tag "li"
        itemLista.innerText = `${usuario.id} ${usuario.nome_completo}`; // innertext coloca texto dentro da tag selecionada
        lista.appendChild(itemLista); // O appendchild coloca "itemList" dentro da tag da classe lista, que no caso é uma "ul"
        });
    })
    .catch(error => console.error(error));
}


// função para limpar os campos do formulário
function limpar() {
    Inome.value = "",
    Iusuario.value = "",
    Iemail.value = "",
    Isenha.value = "",
    Itelefone.value = ""
};

listar();

// Botão de cadastro
formulario.addEventListener ('submit', function(event) {
    event.preventDefault();

    cadastrar();
    limpar(); // limpando os campos do formulário após cadastrar
});

// Botão de atualizar lista
botaoLista.addEventListener ('click', function() {
    lista.innerHTML = ""; // esvaziando a lista
    listar(); // adicionando a lista atualizada
});