import React, {useState, useEffect} from 'react'
import {Link, useHistory, useParams} from 'react-router-dom';

import UserService from "../services/UserService";





const AddUserComponent = () => {

    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [email, setEmail] = useState('')
    const [phone, setPhone] = useState('')
    const history = useHistory();
    const {id} = useParams();

    const saveOrUpdateUser = (e) => {
        e.preventDefault();

        const user = {username, password, email, phone}

        if(id){
            UserService.updateUser(id, user).then((response) => {
                history.push('/users')
            }).catch(error => {
                console.log(error)
            })

        }else{
           UserService.createUser(user).then((response) =>{

                console.log(response.data)

                history.push('/users');

            }).catch(error => {
                console.log(error)
            })
        }

    }

    useEffect(() => {

        UserService.getUserById(id).then((response) =>{
            setUsername(response.data.username)
            setPassword(response.data.password)
            setEmail(response.data.email)
            setPhone(response.data.phone)
        }).catch(error => {
            console.log(error)
        })
    }, [])

    const title = () => {

        if(id){
            return <h2 className = "text-center">Update User</h2>
        }else{
            return <h2 className = "text-center">Add User</h2>
        }
    }

    return (
        <div>
            <br/>
            <br/>
            <div className="container">
                <div className="row">
                    <div className="card col-md-6 offset-md-3 offset-md-3">
                        {
                            title()
                        }
                        <div className="card-body">
                            <form>
                                <div className="form-group mb-2">
                                    <label className="form-label">Username :</label>
                                    <input
                                        type = "text"
                                        placeholder = "Enter username"
                                        name = "username"
                                        className = "form-control"
                                        value = {username}
                                        onChange = {(e) => setUsername(e.target.value)}
                                    >
                                    </input>
                                </div>
                                <div className="form-group mb-2">
                                    <label className="form-label">Password :</label>
                                    <input
                                        type = "text"
                                        placeholder = "Enter password"
                                        name = "password"
                                        className = "form-control"
                                        value = {password}
                                        onChange = {(e) => setPassword(e.target.value)}
                                    >
                                    </input>
                                </div>
                                <div className="form-group mb-2">
                                    <label className="form-label">Email :</label>
                                    <input
                                        type = "text"
                                        placeholder = "Enter email"
                                        name = "email"
                                        className = "form-control"
                                        value = {email}
                                        onChange = {(e) => setEmail(e.target.value)}
                                    >
                                    </input>
                                </div>
                                <div className="form-group mb-2">
                                    <label className="form-label">Phone :</label>
                                    <input
                                        type = "text"
                                        placeholder = "Enter phone"
                                        name = "phone"
                                        className = "form-control"
                                        value = {phone}
                                        onChange = {(e) => setPhone(e.target.value)}
                                    >
                                    </input>
                                </div>
                                <button className = "btn btn-success" onClick = {(e) => saveOrUpdateUser(e)} >Submit </button>
                                <Link to="/users" className="btn btn-danger"> Cancel </Link>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}


export default AddUserComponent