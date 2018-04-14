import firebase from 'firebase';

const config = {
  apiKey: "AIzaSyCYBfPYLs2T0ndsPqMyjNUbmE2UW9l1D8E",
  authDomain: "knowwhere-a34eb.firebaseapp.com",
  databaseURL: "https://knowwhere-a34eb.firebaseio.com",
  projectId: "knowwhere-a34eb",
  storageBucket: "knowwhere-a34eb.appspot.com",
  messagingSenderId: "575699739897"
  };
  firebase.initializeApp(config);

  //const database = firebase.database();

  export default firebase;