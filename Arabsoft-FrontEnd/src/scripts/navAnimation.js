document.addEventListener('DOMContentLoaded', () => {
    const navLinks = document.querySelectorAll('#cs-navigation .cs-li-link');
    let activeLink;

    const updateUnderline = (targetLink) => {
        if (activeLink) {
            activeLink.classList.remove('cs-active');
        }
        targetLink.classList.add('cs-active');
        
        const underline = document.querySelector('.underline') || document.createElement('span');
        underline.className = 'underline';
        document.querySelector('#cs-navigation').appendChild(underline);

        const linkRect = targetLink.getBoundingClientRect();
        const navRect = document.querySelector('#cs-navigation').getBoundingClientRect();

        underline.style.width = `${linkRect.width}px`;
        underline.style.transform = `translateX(${linkRect.left - navRect.left}px)`;

        activeLink = targetLink;
    };

    const setActiveLinkBasedOnURL = () => {
        const currentPath = window.location.pathname;
        navLinks.forEach(link => {
            if (link.getAttribute('href') === currentPath) {
                updateUnderline(link);
            }
        });
    };

    navLinks.forEach(link => {
        link.addEventListener('click', (e) => {
            e.preventDefault();
            updateUnderline(link);
            setTimeout(() => {
                window.location.href = link.href;
            }, 300); // Delay to allow the animation to complete
        });
    });

    // Set the correct active link on page load
    setActiveLinkBasedOnURL();
});
