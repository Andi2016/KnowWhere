import React from 'react';
import PrivateHeader from './PrivateHeader';

const ChatPage = () => (
    <div>
    <PrivateHeader />
    <div className="content-container">
      <h3 className="content-container__groupname"> Dinner Group </h3>
      <input 
      type="text"
      className="post-input"
      placeholder="Type..."
      />
    </div>
    
      
    
    </div>
);

export default ChatPage;