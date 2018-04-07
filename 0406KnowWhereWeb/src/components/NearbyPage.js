import React from 'react';
import NearbyList from './NearbyList';
import PrivateHeader from './PrivateHeader';

class NearbyPage extends React.Component{
  render(){
    return (
      <div>
      <PrivateHeader />
      <NearbyList />
    </div>
    );
  }
}
    


export default NearbyPage;