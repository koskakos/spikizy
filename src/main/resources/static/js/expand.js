var coll = document.getElementsByClassName("collapsible");

for (var i = 0; i < coll.length; i++) {
    coll[i].addEventListener("click", function () {

        var content = this.previousElementSibling;
        var wrapper = document.querySelector(".data__wrapper")
        var icon = this.getElementsByTagName('i')[0];
        var text = this.getElementsByTagName('span')[0];

        if (content.style.maxHeight) {
            icon.style.transform = "rotate(0deg)";
            content.style.maxHeight = null;
            text.innerHTML = "See More";
            wrapper.style.display = 'block';
        } else {
            icon.style.transform = "rotate(-180deg)";
            content.style.maxHeight = content.scrollHeight + "px";
            text.innerHTML = "See Less";
            wrapper.style.display = 'none';
        }
    });
}

var coll2 = document.getElementsByClassName("collapsible2");

for (i = 0; i < coll2.length; i++) {
    coll2[i].addEventListener("click", function () {

        var content = this.nextElementSibling;
        var icon = this.getElementsByTagName('i')[0];

        if (content.style.maxHeight) {
            icon.style.transform = "rotate(0deg)";
            content.style.maxHeight = null;
        } else {
            icon.style.transform = "rotate(-180deg)";
            content.style.maxHeight = content.scrollHeight + "px";
        }
    });
}

var panel = document.getElementById("coursepanel");

window.onscroll = function () {
    if (window.scrollY < 250) {
        panel.style.display = 'flex';
    } else {
        panel.style.display = 'none';
    }
}