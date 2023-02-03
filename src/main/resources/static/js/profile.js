const profileButtons = document.getElementsByClassName('profbtn');
const profileBlocks = document.getElementsByClassName('prof__block');

for (var i = 0; i < profileButtons.length; i++) {
    profileButtons[i].addEventListener("click", function () {
        for (var j = 0; j < profileBlocks.length; j++) {
            if (profileBlocks[j].getAttribute('data-state') === "opened") {
                profileBlocks[j].setAttribute('data-state', "closed");
            }
        }
    });
}

profileButtons[0].addEventListener('click', () => {
    profileBlocks[0].setAttribute('data-state', "opened")
})

profileButtons[1].addEventListener('click', () => {
    profileBlocks[1].setAttribute('data-state', "opened")
})

const fileName = document.querySelector(".file-name");
const defaultBtn = document.querySelector(".defaultBtn");
const downBtn = document.querySelector(".download");
const ava = document.querySelector('.ava-image');
let regExp = /[0-9a-zA-Z\^\&\'\@\{\}\[\]\,\$\=\!\-\#\(\)\.\%\+\~\_ ]+$/;
downBtn.addEventListener('click', () => {
    defaultBtn.click();
})

defaultBtn.addEventListener('change', function () {
    const file = this.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = function () {
            const result = reader.result;
            ava.src = result;
        }
        reader.readAsDataURL(file);
    }
    if (this.value) {
        let valueStore = this.value.match(regExp);
        fileName.value = valueStore;
    }
});

