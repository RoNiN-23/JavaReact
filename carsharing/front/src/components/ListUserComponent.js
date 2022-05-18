import React, {useEffect, useState} from "react";
import UserService from "../services/UserService";
import {Link} from "react-router-dom";

const ListUserComponent = () => {

    const [users, setUsers] = useState([])

    useEffect(() => {
            UserService.getAllUsers().then((response) => {
                setUsers(response.data)
                console.log(response.data);
            }).catch(error =>{
                console.log(error);
            })
        },[])

    return (
        <div className="container">
            <h2 className="text-center">List Users</h2>
            <Link to = "/add-user" className = "btn btn-primary mb-2" >Add User</Link>
            <table className="table table-bordered table-striped">
                <thead>
                <th>ID</th>
                <th>USERNAME</th>
                <th>PASSWORD</th>
                <th>EMAIL</th>
                <th>PHONE</th>
                <th>CARID</th>
                <th>ORDERID</th>
                <th>WALLET</th>
                <th>ACTION</th>
                </thead>
                <tbody>
                {
                    users.map(
                        user =>
                            <tr key={user.id}>
                                <td>{user.id}</td>
                                <td>{user.username}</td>
                                <td>{user.password}</td>
                                <td>{user.email}</td>
                                <td>{user.phone}</td>
                                <td>{user.carId}</td>
                                <td>{user.orderId}</td>
                                <td>{user.wallet}</td>
                                <td><Link className = "btn btn-info" to={`/edit-user/${user.id}`}>UPDATE</Link></td>
                            </tr>
                    )
                }
                </tbody>
            </table>
        </div>
    )
}
export default ListUserComponent