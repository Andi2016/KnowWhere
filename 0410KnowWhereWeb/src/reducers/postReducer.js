

const postReducerDefultState = {
    postText: '',
    coordinates: ''
}

export default (state=postReducerDefultState, action) => {
    switch(action.type){
        case 'ADD_POST':
          return action.postText;
        default :
          return state;
    }
};