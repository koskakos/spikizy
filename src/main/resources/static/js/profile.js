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

profileButtons[2].addEventListener('click', () => {
    profileBlocks[2].setAttribute('data-state', "opened")
})

const fileName = document.querySelector(".file-name");
const defaultBtn = document.querySelector(".defaultBtn");
const upBtn = document.querySelector(".upload");
const ava = document.querySelector('.ava-image');
let regExp = /[0-9a-zA-Z\^\&\'\@\{\}\[\]\,\$\=\!\-\#\(\)\.\%\+\~\_ ]+$/;
upBtn.addEventListener('click', (event) => {
    event.preventDefault();
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

var el = document.getElementById('Change');

function isfilled() {
    if (el.repass.value != "") passCheck();
}

function passCheck() {
    var old = el.old.value;
    var newp = el.new.value;
    var repass = el.repass.value;
    var submit = el.submit;
    submit.disabled = true;
    var fail = "";

    if (newp != repass) fail = "Passwords must be same";
    document.getElementById("error").innerHTML = fail;
    if (fail == "") {
        submit.disabled = false;
    }
}