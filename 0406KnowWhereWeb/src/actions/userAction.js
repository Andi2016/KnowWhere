
export const logIn = (user) => ({
    type: 'LOGIN',
    user
});



export const getUser = (user) => ({
    type: 'GET_USER',
    user
})

export const getGroupname = (friend) => ({
    type: 'GET_GROUP',
    text: friend
})
