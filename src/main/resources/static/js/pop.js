const log = document.querySelector('.log');
const logwrap = document.querySelector('.log__wrapper');

const reg = document.querySelector('.reg');
const regwrap = document.querySelector('.reg__wrapper');

const forgot = document.querySelector('.forgot');
const forgotwrap = document.querySelector('.forgot__wrapper');

const body = document.querySelector('body');

function logPop() {
    const isRegOpened = reg.getAttribute('aria-expanded') === "true";
    if (isRegOpened) closeReg();
    openLog();
};

function openLog() {
    log.setAttribute('aria-expanded', "true");
    logwrap.setAttribute('data-state', "opened");
    body.style.overflow = 'hidden';
};

function closeLog() {
    log.setAttribute('aria-expanded', "false");
    logwrap.setAttribute('data-state', "closed");
    body.style.overflow = 'visible',
        { once: true }
};

function regPop() {
    const isLogOpened = log.getAttribute('aria-expanded') === "true";
    if (isLogOpened) closeLog();
    openReg();
};

function openReg() {
    reg.setAttribute('aria-expanded', "true");
    regwrap.setAttribute('data-state', "opened");
    body.style.overflow = 'hidden';
};

function closeReg() {
    reg.setAttribute('aria-expanded', "false");
    regwrap.setAttribute('data-state', "closing");

    regwrap.addEventListener('animationend', () => {
        setAttribute('data-state', "closed");
        body.style.overflow = 'visible'
    }, { once: true })
};

function forgotPop() {
    const isLogOpened = log.getAttribute('aria-expanded') === "true";
    if (isLogOpened) closeLog();
    openForgot();
};

function openForgot() {
    forgot.setAttribute('aria-expanded', "true");
    forgotwrap.setAttribute('data-state', "opened");
    body.style.overflow = 'hidden';
};

function closeForgot() {
    forgot.setAttribute('aria-expanded', "false");
    forgotwrap.setAttribute('data-state', "closed");
    body.style.overflow = 'visible',
        { once: true }
};