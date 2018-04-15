import React from 'react';
import PostBoard from './PostBoard';
import PostFilter from './PostFilter';
import NearbyList from './NearbyList';
import PrivateHeader from './PrivateHeader';

const NearbyPage = () => (
    <div>
      <PrivateHeader />
      <PostBoard />
      <PostFilter />
      <NearbyList />
    </div>
);

export default NearbyPage;