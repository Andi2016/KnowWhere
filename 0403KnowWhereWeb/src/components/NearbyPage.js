import React from 'react';
import PostMessageBoard from './PostMessageBoard';
import PostFilter from './PostFilter';
import NearbyList from './NearbyList';

const NearbyPage = () => (
    <div>
      <PostMessageBoard />
      <PostFilter />
      <NearbyList />
    </div>
);

export default NearbyPage;