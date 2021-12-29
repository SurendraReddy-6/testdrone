import axios from "axios";
import { cmsApi, prodApi } from "./instances.json";

let baseUrl =
  window.location.host.startsWith("localhost") ||
  window.location.host.startsWith("qa")
    ? cmsApi
    : prodApi;

// Add a request interceptor
axios.interceptors.request.use(
  (config) => {
    config.headers["Authorization"] = "Bearer "
    if(localStorage.getItem("authToken") && config.url !== "/login" && config.url !== "/api/tenant/list"){
      config.headers["Authorization"] = "Bearer " + localStorage.getItem("authToken");
    }
    if(config.url !== "/tenant/list") {
      config.headers["X-Tenant"] = localStorage.getItem("tenantCode");
    } else {
      config.headers["X-Tenant"] = "cms"
    }
    return config;
  },
  (error) => {
    console.log(error)
    Promise.reject(error);
  }
);

//Add a response interceptor
axios.interceptors.response.use(
  (response) => {
    return response;
  },
  function (error) {
    console.log(error);
    // if (error.response.status === 401) {
    //   if (localStorage.getItem("auth") === "true") {
    //     const originalRequest = error.config;
    //     axios.defaults.headers.common["Authorization"] = "Bearer " + localStorage.getItem("authToken");
    //     return axios(originalRequest);
    //   } else {
    //     return Promise.reject(error);
    //   }
    // }
    return Promise.reject(error);
  }
);

axios.defaults.baseURL = baseUrl;
axios.defaults.headers["Content-Type"] = "application/json";
axios.defaults.headers["Access-Control-Allow-Headers"] = "*";
axios.defaults.headers["Access-Control-Allow-origin"] = "*";

export const io = async ({ url, method, data, params, headers }) => {
  // refer here : https://www.npmjs.com/package/axios#axiosrequestconfig-1
  return await axios({
    url,
    method,
    headers,
    params,
    data,
  });
};
