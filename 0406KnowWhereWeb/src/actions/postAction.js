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

// ADD_POST
export const addPost = (post) => ({
    type: 'ADD_POST',
    post
  });
  
export const startAddPost = (postData = {}) => {
    return (dispatch, getState) => {
      const username = getState().user.username;
      const {
        postText = '',
        coordinates = '',
      } = postData;
      const post = { postText, coordinates };
      return axios.put(`/user/${username}/whatsup`, {postText}, axiosConfig)
                  .then((response)=>{
                      dispatch(addPost({
                          ...post
                      }))
                  })
                  .catch((error)=>{
                    console.log(error)})
      /**return database.ref(`user/${username}/whatsup`).push(expense).then((ref) => {
        dispatch(addExpense({
          id: ref.key,
          ...expense
        })); */
    };
};
