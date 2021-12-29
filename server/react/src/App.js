import React from "react";
import "./App.css";
import 'antd/dist/antd.css';
import AuthProvider from "./Pages/context/AuthContext"
import LayoutComponent from "./Pages/nav/Layout"

function App() {
  return (
      <AuthProvider>
        <LayoutComponent />
      </AuthProvider>
  );
}

export default App;
