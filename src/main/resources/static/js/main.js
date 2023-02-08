const packs = [...document.querySelectorAll('.slider-line')];
const nextbtn = [...document.querySelectorAll('.rightbutton')];
const prevbtn = [...document.querySelectorAll('.leftbutton')];


packs.forEach((item, i) => {
    let offset = 0;

    setInterval(() => {
        if (i != 2) offset += 19.2;
        else offset += 25.5;
        if (offset > 76.8) offset = 0;
        packs[i].style.left = -offset + 'vw';
    }, 10000);

    nextbtn[i].addEventListener('click', () => {
        if (i != 2) offset += 19.2;
        else offset += 25.5;
        if (offset > 76.8) offset = 0;
        packs[i].style.left = -offset + 'vw';
    });

    prevbtn[i].addEventListener('click', () => {
        if (i != 2) offset -= 19.2;
        else offset -= 25.5;
        if (offset < -1) offset = 76.8;
        packs[i].style.left = -offset + 'vw';

    });
});

const pagination = document.querySelectorAll('.pagination');

for (var i = 0; i < pagination.length; i++) {
    var pages = pagination[i].querySelectorAll('.page');
    for (var j = 0; j < pages.length; j++) {
        pages[j].addEventListener('click', function () {
            var current = document.getElementsByClassName("active");
            current[0].className = current[0].className.replace(" active", "");
            this.className += " active";
        });
    }
}

// hamburger
const menuToggle = document.querySelector('.menu-toggle');
const siteMenu = document.querySelector('.menu');

menuToggle.addEventListener('click', () => {
    const isOpened = menuToggle.getAttribute('aria-expanded') === "true";
    if (isOpened ? closeMenu() : openMenu());
});

function openMenu() {
    menuToggle.setAttribute('aria-expanded', "true");
    siteMenu.setAttribute('data-state', "opened");
}

function closeMenu() {
    menuToggle.setAttribute('aria-expanded', "false");
    siteMenu.setAttribute('data-state', "closing");

    siteMenu.addEventListener('animationend', () => {
        siteMenu.setAttribute('data-state', "closed");
    }, { once: true })
}