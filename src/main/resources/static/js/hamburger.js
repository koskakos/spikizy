// =============================================== hamburger
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