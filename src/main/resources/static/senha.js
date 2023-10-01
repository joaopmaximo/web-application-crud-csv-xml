const urlParams = new URLSearchParams(window.location.search);
const id = urlParams.get('id');
const divP = document.querySelector('.div-principal2');
const senhaAntiga = document.querySelector('#senhaAntiga');
const senhaNova = document.querySelector('#senhaNova');
let senhaBanco;
const formulario = document.querySelector('form');
const back = document.querySelector('#back');
back.href = `./usuario.html?id=${id}`;
const descricao = document.querySelector('#descricao');
const urlApi = "https://web-application-crud-csv-xml.onrender.com/usuarios";

function getSenhaAntiga() {
    return new Promise ((resolve, reject) => {
        fetch(`${urlApi}/${id}`)
        .then(response => response.json())
        .then (usuario => {
            senhaBanco = `${usuario.senha}`;
        })
        .then(response => resolve(response))
        .catch(error => reject(error))
    })
}

function atualizar() {
    fetch(`${urlApi}/${id}`, {
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        method: 'PUT',
        body: JSON.stringify({
            senha: senhaNova.value
        })
    })
    .then (response => {
        console.log(response);
    })
    .catch (error => console.error(error))
}

formulario.addEventListener('submit', async function(event) {
    event.preventDefault();
    await getSenhaAntiga();
    if (senhaBanco == senhaAntiga.value) {
        atualizar();
        descricao.textContent = 'Senha alterada com sucesso!';
    }
    else {
        descricao.textContent = 'Senha inválida!';
    }
})