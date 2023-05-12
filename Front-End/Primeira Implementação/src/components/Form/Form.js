import React, { useState } from "react";

/* CSS Archives */
import "./Form.css";
import "animate.css";

/*
    npm install node-fetch -> para as req do back end
    npm install animate.css --save -> animations
    Realistic 3d geometric background white -> Background images in site: www.freepik.com
*/

export default function Form() {
    const [inputName, setInputName] = useState();
    const [inputEmail, setInputEmail] = useState();
    const [inputPassword, setInputPassword] = useState();
    
    const images = ['https://img.freepik.com/free-vector/abstract-background-with-3d-pattern_1319-68.jpg?w=826&t=st=1683852435~exp=1683853035~hmac=b59f99664ec733db71f54fbd9e3458fbeac11afcd2eccad24ddc22c0b8efa5e4', 'https://img.freepik.com/free-photo/modern-background-with-geometrical-shapes_23-2148811486.jpg?w=1380&t=st=1683854180~exp=1683854780~hmac=30786aa673df7db778d57041aea00618f0e26321bb0b36836dbaa718e8eb03d2', 'https://img.freepik.com/free-photo/high-angle-creative-background-with-grey-shapes_23-2148811502.jpg?w=1380&t=st=1683854144~exp=1683854744~hmac=7fd6012a4ba7da1551695763dc9265d15f52361096ca401aa1f1cdc8c2869fee', 'https://img.freepik.com/free-photo/3d-elegant-geometrical-texture-background_23-2149073281.jpg?w=1380&t=st=1683854675~exp=1683855275~hmac=ca89952cd58a451968ccb6cea717611d991cab3ddc07cad5c6ec61429d679ff1'];

    function handleSubmit(event) {
        event.preventDefault();
    }

    return (
        <div className="form-main-container">
            <img className='form-image-output animate__animated animate__fadeInDown' src={images[0]} alt="Form Image"/>

            <form onSubmit={handleSubmit} className="form-form-container was-validated animate__animated animate__fadeInDown">
                <p className='form-title-output'>Create your account</p>
                <div class="form-floating mb-3">
                    <input
                        type="text"
                        className="form-control"
                        placeholder="Name"
                        required
                        minLength={5}
                        maxLength={45}
                        value={inputName}
                        onChange={(event) => setInputName(event.target.value)}
                    />
                    <label for="floatingInput">Username</label>
                    <div class="valid-feedback animate__animated animate__fadeInDown">Looks good!</div>
                    <div class="invalid-feedback animate__animated animate__fadeInDown">Invalid field.</div>
                </div>

                <div class="form-floating mb-3">
                    <input
                        type="email"
                        className="form-control"
                        placeholder="E-Mail"
                        required
                        minLength={4}
                        maxLength={60}
                        value={inputEmail}
                        onChange={(event) => setInputEmail(event.target.value)}
                    />
                    <label for="floatingInput">E-Mail</label>
                    <div class="valid-feedback animate__animated animate__fadeInDown">Looks good!</div>
                    <div class="invalid-feedback animate__animated animate__fadeInDown">Invalid field.</div>
                </div>

                <div class="form-floating mb-3">
                    <input
                        type="password"
                        className="form-control"
                        placeholder="Password"
                        required
                        minLength={4}
                        maxLength={45}
                        value={inputPassword}
                        onChange={(event) => setInputPassword(event.target.value)}
                    />
                    <label for="floatingInput">Password</label>
                    <div class="valid-feedback animate__animated animate__fadeInDown">Looks good!</div>
                    <div class="invalid-feedback animate__animated animate__fadeInDown">Invalid field.</div>
                </div>

                <div className="d-grid gap-2">
                    <button type="submit" className="btn btn-primary">
                        <strong>Create account</strong>
                    </button>
                </div>
        
                <br/>
                <p className='form-text-alredy animate__animated animate__fadeInDown'>Already have account?&nbsp;<strong className='form-text-alredy-sign-in'>Sign In</strong></p>
            </form>
        </div>
    );
}
