// Exemplu de validare pentru formularul de adăugare produs
document.addEventListener("DOMContentLoaded", function() {
    const form = document.querySelector("form");

    form.addEventListener("submit", function(event) {
        const numeProdus = document.querySelector("input[name='numeProdus']").value;
        if (!numeProdus) {
            alert("Te rog completează numele produsului!");
            event.preventDefault(); // Opresc trimiterea formularului
        }
    });
});
