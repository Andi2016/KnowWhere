

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
          user
        };
      case 'LOGOUT':
        return {};
      default:
        return state;
    }
  };
  