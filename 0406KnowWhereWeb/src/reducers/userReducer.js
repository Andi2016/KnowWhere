

const userReducerDefaultState = {
    username: '',
    email: '',
    password: '',
    correctPass:'',
    firstname:'',
    lastname:'', 
    groupname:'',
    postText:'',
    status: '',
    coordinate: {
      latitude: '',
      longitude: ''
    },
    friends:[],
    groups:[],
    photoURL: '',
    nearbyArray: []
};


export default (state = userReducerDefaultState, action) => {
    switch (action.type) {
      case 'LOGIN':
        return {
          ...state,
          username: action.user.username,
          password: action.user.password
        };
      case 'LOGOUT':
        return {};
      case 'GET_USER':
        return {
          ...state,
          email: action.user.email,
          firstname: action.user.firstname,
          lastname: action.user.lastname,
          friends: action.user.friends,
          groups: action.user.groups,
          postText: action.user.postText,
          coordinate: action.user.coordinate,
          photoURL:aciton.user.photoURL
        };
        case 'GET_GROUP':
          return {
            ...state,
            firstname: action.text
          }
        case "UPDATE_GROUP":
          return {
            ...state,
            firstname: action.payload
          }
      default:
        return state;
    }
  };
  