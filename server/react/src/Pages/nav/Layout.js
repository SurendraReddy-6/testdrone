import React, { useState, useEffect, useContext } from "react";
import { Layout } from "antd";
import { Content } from "antd/lib/layout/layout";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import HeaderComponent from "./HeaderComponent";
import { MenuUnfoldOutlined, MenuFoldOutlined } from "@ant-design/icons";
import FooterComponent from "./FooterComponent";
import Home from "../Home";
import DobleTestResults from "../doble/ResultsHeader";
import ImpulseResults from "../impulse/impulse";


const TenantContext = React.createContext({});
export const useTenantContext = () => useContext(TenantContext);

function LayoutComponent() {
  const [collapsed, setCollapsed] = useState(false);
  const [isAuthenticated, setIsAuthenticated] = useState(localStorage.getItem("isAuthenticated"));

  useEffect(() => {
  }, [])
  
  const iconImg = React.createElement(
    collapsed ? MenuUnfoldOutlined : MenuFoldOutlined,
    {
      className: "trigger",
      onClick: () => setCollapsed(!collapsed),
    }
  );

  return (
    <React.Fragment>
      <Router>
          <React.Fragment>
            <HeaderComponent 
              // icon={iconImg} 
              isAuthenticated={isAuthenticated} 
              setIsAuthenticated={setIsAuthenticated} 
            />
                  <Layout className="main-layout">
                    <Content className="site-layout-background main-container">
                      <Switch>
                        <Route exact path="/" component={Home} />
                        <Route exact path="/test-results" component={DobleTestResults} />
                        <Route exact path="/impulse-results" component={ImpulseResults} />
                        {/* <Route exact path="/models" component={ModelsComponent} /> */}
                      </Switch>
                      <div className="clearfix"></div>
                    </Content>
                    <div className="clearfix"></div>
                  </Layout>
            <FooterComponent  />
          </React.Fragment>
      </Router>
    </React.Fragment>
  );
}

export default LayoutComponent;
