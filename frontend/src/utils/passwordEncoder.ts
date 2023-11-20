import bcrypt from "bcryptjs";

async function passwordEncoder(password: string) {
  const salt = process.env.REACT_APP_PASSWORD_SALT;
  const salt2 = await bcrypt.genSalt(123);

  bcrypt.hash(password, salt2, (error, hash) => {
    if (error) {
      console.log(error);
    } else {
      console.log(hash);
    }
  });
}

export default passwordEncoder;
