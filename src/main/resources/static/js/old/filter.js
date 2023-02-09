const filter = document.getElementsByClassName('filter')[0];
const features = document.getElementsByClassName('features')[0];
const courses = document.getElementsByClassName('courses')[0];
const wrapper = document.getElementsByClassName('wrapper')[0];

filter.addEventListener('click', () => {
    const isOpened = filter.getAttribute('aria-expanded') === "true";
    if (isOpened ? closeMenu() : openMenu());
});

function openMenu() {
    filter.setAttribute('aria-expanded', "true");
    features.setAttribute('data-state', "opened");
    courses.style.width = "78%";
    wrapper.setAttribute('data-state', "opening");

    wrapper.addEventListener('animationend', () => {
        wrapper.setAttribute('data-state', "static");
    }, { once: true })
}

function closeMenu() {
    filter.setAttribute('aria-expanded', "false");
    wrapper.setAttribute('data-state', "closing");

    wrapper.addEventListener('animationend', () => {
        features.setAttribute('data-state', "closed");
        wrapper.setAttribute('data-state', "static");
        courses.style.width = "100%";
    }, { once: true })
}

var coll = document.getElementsByClassName("collapsible");

for (var i = 0; i < coll.length; i++) {
    coll[i].addEventListener("click", function () {

        var content = this.nextElementSibling;
        var icon = this.getElementsByTagName('i')[0];
        var text = this.getElementsByTagName('span')[0];

        if (content.style.maxHeight) {
            icon.style.transform = "rotate(0deg)";
            content.style.maxHeight = null;

        } else {
            icon.style.transform = "rotate(-180deg)";
            content.style.maxHeight = content.scrollHeight + "px";
        }
    });
}

const range = document.querySelectorAll(".range__input input");
const progress = document.querySelector(".range__progress");
const price = document.querySelectorAll(".range input");

let gap = 500;

range.forEach(input => {
    input.addEventListener('input', e => {
        let min = parseInt(range[0].value),
            max = parseInt(range[1].value);

        if (max - min < gap) {
            if (e.target.className === 'range__min')
                range[0].value = max - gap;
            else range[1].value = min + gap;
        }
        else {
            price[0].value = min;
            price[1].value = max;
            progress.style.left = (min / range[0].max) * 100 + "%";
            progress.style.right = 100 - (max / range[1].max) * 100 + "%";
        }
    })
});

price.forEach(input => {
    input.addEventListener('input', e => {
        let min = parseInt(price[0].value),
            max = parseInt(price[1].value);

        if (max - min >= gap && max <= 10000) {
            if (e.target.className === 'range__text-min') {
                range[0].value = min;
                progress.style.left = (min / range[0].max) * 100 + "%";
            } else {
                range[1].value = max;
                progress.style.right = 100 - (max / range[1].max) * 100 + "%";
            }
        }
    })
});