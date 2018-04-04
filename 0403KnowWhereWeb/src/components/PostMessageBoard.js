import React from 'react';

const PostMessageBoard = () => (
    <div className="page-header">
      <div className = "content-container">
        <input 
        type="text"
        className="post-input"
        placeholder="Share your post here."
        />
        <button className="button">POST</button>
      </div>
    </div>
);

export default PostMessageBoard;