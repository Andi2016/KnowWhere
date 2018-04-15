import React from 'react';

const PostFilter = () => (
  <div className="content-container">
    <div className="input-group">
      <div className="input-group__item">
        <input 
        type="text"
        className="text-input"
        placeholder="Search Posts"
        />
        <button className="button">search</button>
      </div>
    </div>
  </div>
);

export default PostFilter;