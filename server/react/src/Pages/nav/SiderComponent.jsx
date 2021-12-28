import React from "react";
import { Layout, Menu } from "antd";
import { Link } from "react-router-dom";
import { useLocation } from "react-router";

const { Sider } = Layout;

function SiderComponent(props) {
  const location = useLocation();

  return (
    <>
      <Sider
        theme="dark"
        trigger={null}
        collapsible
        collapsed={props.collapsed}
        collapsedWidth="0"
        breakpoint="lg"
        width={200}
        style={{ minHeight: "90vh", maxHeight: "auto" }}
      >
        <Menu
          theme="dark"
          mode="inline"
          defaultSelectedKeys={["/users"]}
          selectedKeys={[location.pathname]}
          style={{ height: "100%", borderRight: 0 }}
        >
          <Menu.Item key="/home">
            <Link to="/home">Home</Link>
          </Menu.Item>
        </Menu>
      </Sider>
    </>
  );
}
export default SiderComponent;
