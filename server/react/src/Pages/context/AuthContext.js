import React, { createContext, useContext, useState } from "react"

export const AuthContext = createContext({});


export const setAccessToken = (token) => {
};

const AuthProvider = ({ children }) => {
    const[user, setUser] = useState({role: localStorage.getItem("role")?.split(","), tenant: localStorage.getItem("tenant")})
    const loginForAuth = (data) => {
        const { accessToken, role, tenantName } = data;
        setAccessToken(accessToken)
        setUser({
            role: role.split(","),
            tenant: tenantName
        })
    };

    const hasRole = (name) => {
        return user?.role?.some(rolename => rolename===name)
    }

    return (
        <React.Fragment>
            <AuthContext.Provider
                value={{
                    loginForAuth,
                    user,
                    hasRole
                }}
            >
                {children}
            </AuthContext.Provider>
        </React.Fragment>
    )
}

export default AuthProvider;

export const useAuthDetails = () => {
    const userDetails = useContext(AuthContext);
    return userDetails ? userDetails : {};
};

export function useAuth() {
    return useContext(AuthContext);
}