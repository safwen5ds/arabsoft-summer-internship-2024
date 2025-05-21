import React, { useState } from "react";
import "../styles/Nav.css";

const Nav = () => {
  const [activeLink, setActiveLink] = useState("/");
  const [isMenuOpen, setMenuOpen] = useState(false);

  const handleClick = (link: React.SetStateAction<string>) => {
    setActiveLink(link);
  };

  const toggleMenu = () => {
    setMenuOpen(!isMenuOpen);
    document.body.classList.toggle("cs-open");
  };

  return (
    <header id="cs-navigation">
      {/* Background Image */}
      <div className="cs-background-image"></div>
      <div className="cs-container">
        <a href="/" className="cs-logo" aria-label="back to home">
          <img
            src="https://www.arabsoft.com.tn/smart/themes/default/assets/img/svg_arabsoft.svg"
            alt="logo"
            aria-hidden="true"
            decoding="async"
          />
        </a>
        <nav className="cs-nav" role="navigation">
         
          <div className="cs-blur-box">
            <div className={`cs-ul-wrapper ${isMenuOpen ? "open" : ""}`}>
              <ul id="cs-expanded" className="cs-ul">
                <li className="cs-li">
                  <a
                    href="/"
                    className={`cs-li-link ${activeLink === "/" ? "cs-active" : ""} bebas-neue-regular`}
                    onClick={() => handleClick("/")}
                  >
                    Home
                  </a>
                </li>
                <li className="cs-li">
                  <a
                    href="/upload"
                    className={`cs-li-link ${activeLink === "/upload" ? "cs-active" : ""} bebas-neue-regular`}
                    onClick={() => handleClick("/upload")}
                  >
                    Upload PDF
                  </a>
                </li>
                <li className="cs-li">
                  <a
                    href="/attestation"
                    className={`cs-li-link ${activeLink === "/attestation" ? "cs-active" : ""} bebas-neue-regular`}
                    onClick={() => handleClick("/attestation")}
                  >
                    liste des attestations d'immatriculation
                  </a>
                </li>
              </ul>
            </div>
          </div>
        </nav>
      </div>
    </header>
  );
};

export default Nav;
