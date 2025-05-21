import React from 'react';
import "../styles/Footer.css"; 

const Footer: React.FC = () => {
    return (
      <footer className="footer">
        <div className="footer-left">
          <div className="footer-logo">
            <img src='src/components/util/svg_arabsoft.png' alt="Arabsoft Logo" />
          </div>
        </div>

        <div className="footer-right">
          <div className="footer-contact">
            <p>
              Contactez-nous<br />
              Rue 8368 -bloc A- Espace El Aziz, lot Ennassim - Montplaisir- 1073 Tunis TUNISIE<br />
              Tél : +216.71.95.12.48<br />
              Fax : +216.71.95.10.44<br />
              Email : <a href="mailto:arabsoft@arabsoft.com.tn">arabsoft@arabsoft.com.tn</a><br />
              <a href="mailto:marketing@arabsoft.com.tn">marketing@arabsoft.com.tn</a>
            </p>
          </div>
        </div>

        <div className="footer-copyright">
          <p>&copy; 2024 ARABSOFT. Tous droits réservés</p>
          <p>Designed & Developed by Gharbi Safwen</p>
        </div>
      </footer>
    );
};

export default Footer;
