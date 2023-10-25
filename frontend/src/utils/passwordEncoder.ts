import bcrypt from "bcryptjs";

const passwordEncoder = (password: string) => {
  const salt = process.env.REACT_APP_PASSWORD_SALT;

  return bcrypt.hash(password, salt + "");
};

export default passwordEncoder;
