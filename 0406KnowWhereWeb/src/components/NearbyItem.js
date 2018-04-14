import React from 'react';

const NearbyItem = ({ username, whatsup, distance}) => (
    <div>
     <div>
        <h3 className="list-item__title">{username}</h3>
        <span className="list-item__subtitle">{distance}</span>
     </div>
     <div>
       <h3 className="list-item__data">{whatsup}</h3>
     </div>
    </div>
);

export default NearbyItem;