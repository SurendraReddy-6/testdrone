import { Footer } from "antd/lib/layout/layout";
import React from "react";

function FooterComponent({ type }) {
  const year = new Date().getFullYear();
  const height = window.innerHeight

  return <Footer className="footer-set">Transformers © {year}</Footer>;
}

export default FooterComponent;
