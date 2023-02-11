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
    document.getElementById("size-error").innerHTML = "";
    if (file.size < 2 * 1024 * 1024) {
        const reader = new FileReader();
        reader.onload = function () {
            const result = reader.result;
            ava.src = result;
        }
        reader.readAsDataURL(file);
    } else {
        document.getElementById("size-error").innerHTML = "File Size Exceeds 2mb";
    }
    if (this.value.match(/(\.jpg|\.png|\.JPG|\.PNG|\.jpeg|\.JPEG)$/)) {
        let valueStore = this.value.match(regExp);
        fileName.value = valueStore;
    } else {
        document.getElementById("size-error").innerHTML = "Invalid File Type";
    }
});

var el = document.getElementById('Change');

function isfilled() {
    if (el.repass.value != "") passCheck();
}

function passCheck() {
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

const passboxes = [...document.querySelectorAll(".pass-box")];
const eyes = [...document.querySelectorAll(".see")];
const passes = [...document.querySelectorAll(".Pass")];
const isVisibles = [0, 0, 0];

passboxes.forEach((item, i) => {
    eyes[i].addEventListener('click', () => {
        if (isVisibles[i]) {
            passes[i].type = 'password';
            isVisibles[i] = 0;
            eyes[i].classList.replace('fa-eye', 'fa-eye-slash');
        } else {
            passes[i].type = 'text';
            isVisibles[i] = 1;
            eyes[i].classList.replace('fa-eye-slash', 'fa-eye');
        }
    })
})
let oldPass = false;
async function checkOldPassword() {
    let password = document.getElementById('password').value;
    // var params = new URLSearchParams('?id=41&password=12345678');
    let data = '?id=41&password=' + password;
    let response = await fetch('../gettest' + data, {
        method: 'POST',
        headers: {
            'Content-type': 'application/json; charset=UTF-8',
            'X-XSRF-TOKEN': csrfToken,
            // id: '41',
            // password: '12345678',
        },
        // body: params
    });
    oldPass = await response.json();
    return oldPass;
}

el.submit.onclick = (event) => {
    event.preventDefault();

    if (oldPass) {
        document.getElementById("error").innerHTML = "";
        el.default.click();
    } else {
        el.new.value = "";
        el.repass.value = "";
        el.old.value = "";
        document.getElementById("error").innerHTML = "Invalid Current Password"
    }
}