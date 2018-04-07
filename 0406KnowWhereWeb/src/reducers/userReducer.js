

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
          ...state,
          username: state.username,
          password: state.password
        };
      case 'LOGOUT':
        return {};
      default:
        return state;
    }
  };
  