import { useState } from "react";
import api from "../api/api";

export default function UserForm({ onCreated }) {
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const submit = async (e) => {
    e.preventDefault();
    await api.post("/users", { username, email, password });
    setUsername(""); setEmail(""); setPassword("");
    onCreated();
  };

  return (
    <form onSubmit={submit}>
      <input placeholder="username" value={username} onChange={e=>setUsername(e.target.value)} />
      <input placeholder="email" value={email} onChange={e=>setEmail(e.target.value)} />
      <input type="password" placeholder="password" value={password} onChange={e=>setPassword(e.target.value)} />
      <button>Create</button>
    </form>
  );
}
