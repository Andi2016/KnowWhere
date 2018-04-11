import axios from 'axios';

axios.defaults.baseURL = 'http://143.215.114.174:8080';
axios.defaults.headers.get['Content-Type'] = 'application/json';
axios.defaults.headers.get['Access-Control-Allow-Origin'] = '*';

    let axiosConfig = {
        headers: {
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': '*',
            'Access-Control-Allow-Method': 'POST, GET, OPTIONS, PUT, DELETE'
    },
        withCredentials: false
    };

export const logIn = (user) => ({
    type: 'LOGIN',
    user
});



export const getUser = (user) => ({
    type: 'GET_USER',
    user
})
