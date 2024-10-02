async function caesarEncrypt(){
    const text = document.getElementById("caesarText").value;
    const shift = document.getElementById("caesarShift").value;
    const res = await fetch(`/encrypt/caesar?text=${text}&shift=${shift}`);
    const result = await res.text();
    console.log(result)
    document.getElementById("caesarResult").textContent = "Encrypted text: " + result ;
}

async function desEncrypt(){
    const data = document.getElementById("desData").value;
    const res = await fetch( `/des/encrypt`, {
        method:'POST',
        headers: {'Content-Type': 'application/x-www-form-urlencoded'},
        body:  `data=${data}`
    })

    const result = await res.text();
    document.getElementById("desResult").textContent = result;

}


async function desDecrypt() {
    const encryptedData = document.getElementById("desEncryptedData").value;
    const base64Key = document.getElementById("desKey").value;
    const response = await fetch(`/des/decrypt`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: `encryptedData=${encryptedData}&base64Key=${base64Key}`
    });
    const result = await response.text();
    document.getElementById("desDecryptResult").textContent = "Decrypted Text: " + result;
}