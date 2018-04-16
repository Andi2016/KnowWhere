import React from 'react';
import { Button, Grid, Col, Row, Image, ListGroup, ListGroupItem } from "react-bootstrap";

const NearbyItem = ({ username, whatsup, distance, photoUrl}) => (
  <Grid>
    <Row>
      <ListGroup className="list-item">
         <ListGroupItem href={`/ChatWindow`} bsStyle="info">
         <p>{username}  {Math.round(distance*100)/100}km </p>   
         <p>{whatsup} </p>      
         </ListGroupItem>      
       </ListGroup>
    </Row>
  </Grid>

);

export default NearbyItem;

/**
 * <div>
     <div>
        <h3 className="list-item__title">{username}</h3>
        <span className="list-item__subtitle">{distance}</span>
     </div>
     <div>
       <h3 className="list-item__data">{whatsup}</h3>
       
     </div>
    </div>
 */

 /**
  *   <Grid>
    <Row>
      <ListGroup>
         <ListGroupItem href={`/chat/${username}`}>
         </ListGroupItem>
         <Col xs={1} md={2}>
         <Image src={photoUrl} rounded />
       </Col>
       <Col xs={3} md={2}>
         <h3> {username}  {distance}km</h3>
         <p>{whatsup}</p>
       </Col>
       </ListGroup>
    </Row>
  </Grid>
  */

  /**
   * <div className="row">
  <div className="col-xs-3"><img src={photoUrl}/></div>
  <div className="col-xs-3">{username}</div>
  <div className="col-xs-6">{whatsup}</div>
  <div className="col-xs-6">{distance}km</div>
</div>
   */