

const userReducerDefaultState = {
    username: '',
    email: '',
    password: '',
    firstname:'',
    lastname:''
};


export default (state = userReducerDefaultState, action) => {
    switch (action.type) {
      case 'LOGIN':
        return {
          username: action.username,
          email: action.email,
          password: action.password,
          firstname: action.firstname,
          lastname: action.lastname
        };
      case 'LOGOUT':
        return {};
      default:
        return state;
    }
  };
  