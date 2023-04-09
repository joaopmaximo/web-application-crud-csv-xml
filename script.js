const formulario = document.querySelector("form");
const Inome = document.querySelector(".nome");
const Iusuario = document.querySelector(".usuario");
const Iemail = document.querySelector(".email");
const Isenha = document.querySelector(".senha");
const Itelefone = document.querySelector(".telefone");

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

function limpar() {
    Inome.value = "",
    Iusuario.value = "",
    Iemail.value = "",
    Isenha.value = "",
    Itelefone.value = ""
};

formulario.addEventListener ('submit', function(event) {
    event.preventDefault();

    cadastrar();
    limpar();
});