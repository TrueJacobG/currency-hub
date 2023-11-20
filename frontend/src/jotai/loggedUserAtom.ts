import { atom } from "jotai";

const loggedUserAtom = atom({ name: "", surname: "", email: "", authCode: "" });

export { loggedUserAtom };
