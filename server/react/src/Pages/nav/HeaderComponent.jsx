import { Button, Row, Col, Image, Menu } from "antd";
import { Link } from "react-router-dom";
import React from "react";
import { useLocation } from "react-router";
import logo from "./spx_logo.jpg";

function HeaderComponent(props) {
  const location = useLocation();

  return (
    <header className="header">
      {location.pathname !== "/" ? (
        <Button type="text" className="toggle menu-button" shape="circle" size="middle" >
          {props.icon}
        </Button>
      ) : null}
          <div className="logo-set">
                <Image src={logo} alt='spx_logo'  />
                <h2>TDMS Integration</h2>
                <div className="header-menu">
                  <Menu mode="inline" defaultSelectedKeys={["/users"]} selectedKeys={[location.pathname]}>
                    <Menu.Item key="/">
                      <Link to="/">Home</Link>
                    </Menu.Item>
                  </Menu>
                </div>
          </div>
    </header>
  );
}

export default HeaderComponent;
