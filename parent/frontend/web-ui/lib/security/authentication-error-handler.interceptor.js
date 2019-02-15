import axios from 'axios'

const addRequestInterceptor = () => {
  axios.interceptors.request.use(function (config) {
    return config;
  }, function (error) {
    return Promise.reject(error);
  })
}

const addResponseInterceptor = () => {
  axios.interceptors.response.use(function (response) {
    return response;
  }, function (error) {
    if (error.response.status === 401){
      window.location.href = `/session/renew?redirect=${window.location.pathname}`
    }

    return Promise.reject(error);
  })
}

export default () => {
  addRequestInterceptor()
  addResponseInterceptor()
}
