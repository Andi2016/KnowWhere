

const userReducerDefaultState = {
    username: '',
    email: '',
    password: '',
    firstname:'',
    lastname:'', 
    postText:'',
    status: '',
    coordinate: {
      latitude: '',
      longitude: ''
    },
    friends:[],
    groups:[],
    photoURL: ''
};


export default (state = userReducerDefaultState, action) => {
    switch (action.type) {
      case 'LOGIN':
        return {
          ...state,
          username: action.username,
          password: action.password
        };
      case 'LOGOUT':
        return {};
      case 'GET_USER':
        return {
          ...state,
          email: state.email,
          firstname: state.firstname,
          lastname: state.lastname,
          friends: state.friends,
          groups: state.groups,
          postText: state.postText,
          coordinate: state.coordinate,
          photoURL:state.photoURL
        };
      default:
        return state;
    }
  };
  