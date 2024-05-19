let savedTexts = document.getElementById("savedTexts");

let savedTitlesString = [];
let savedContentsString = [];

function addText() {
    let titleIntroduced = document.getElementById("titleTextarea").value;
    let contentIntroduced = document.getElementById("contentTextarea").value;
    if (titleIntroduced.trim() !== "") {
        const textId = savedTitlesString.length + 1;

        savedTexts.innerHTML += titleIntroduced + " ";
        savedTexts.innerHTML += `<a href="/text.html?id=${textId}">View the text</a>`;
        savedTexts.innerHTML += "<br>";
        savedTitlesString.push(titleIntroduced);
        savedContentsString.push(contentIntroduced);
        document.getElementById("titleTextarea").value = '';
        document.getElementById("contentTextarea").value = '';

        fetch('/texts', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                title: titleIntroduced,
                content: contentIntroduced
            })
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to save text');
            }
        })
        .catch(error => console.error('Error:', error));
    }
}