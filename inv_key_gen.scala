package AES

import chisel3._
import chisel3.util._

//todo 流水化
class inv_key_gen extends RawModule{
  val io = IO(new INV_KEY_GEN_IO)
  //k0
  io.key_out(0) := io.key_in
  //k1
  val w4 = Wire(UInt(32.W))
  val w5 = Wire(UInt(32.W))
  val w6 = Wire(UInt(32.W))
  val w7 = Wire(UInt(32.W))
  val k1 = Wire(UInt(128.W))
  w4 := XOR32(G_func(io.key_in(127,96),RC_table.rc(0)), io.key_in(31,0))
  w5 := XOR32(w4, io.key_in(63,32))
  w6 := XOR32(w5, io.key_in(95,64))
  w7 := XOR32(w6, io.key_in(127,96))
  k1 := Cat(w7,w6,w5,w4) //encode key
  io.key_out(1) := Inv_key_MC(k1)//decode key
  //k2
  val w8 = Wire(UInt(32.W))
  val w9 = Wire(UInt(32.W))
  val w10 = Wire(UInt(32.W))
  val w11 = Wire(UInt(32.W))
  val k2 = Wire(UInt(128.W))
  w8 := XOR32(G_func(k1(127,96),RC_table.rc(1)), k1(31,0))
  w9 := XOR32(w8, k1(63,32))
  w10 := XOR32(w9, k1(95,64))
  w11 := XOR32(w10, k1(127,96))
  k2 := Cat(w11,w10,w9,w8) //encode key
  io.key_out(2) := Inv_key_MC(k2)//decode key
  //k3
  val w12 = Wire(UInt(32.W))
  val w13 = Wire(UInt(32.W))
  val w14 = Wire(UInt(32.W))
  val w15 = Wire(UInt(32.W))
  val k3 = Wire(UInt(128.W))
  w12 := XOR32(G_func(k2(127,96),RC_table.rc(2)), k2(31,0))
  w13 := XOR32(w12, k2(63,32))
  w14 := XOR32(w13, k2(95,64))
  w15 := XOR32(w14, k2(127,96))
  k3 := Cat(w15,w14,w13,w12) //encode key
  io.key_out(3) := Inv_key_MC(k3)//decode key
  //k4
  val w16 = Wire(UInt(32.W))
  val w17 = Wire(UInt(32.W))
  val w18 = Wire(UInt(32.W))
  val w19 = Wire(UInt(32.W))
  val k4 = Wire(UInt(128.W))
  w16 := XOR32(G_func(k3(127,96),RC_table.rc(3)), k3(31,0))
  w17 := XOR32(w16, k3(63,32))
  w18 := XOR32(w17, k3(95,64))
  w19 := XOR32(w18, k3(127,96))
  k4 := Cat(w19,w18,w17,w16) //encode key
  io.key_out(4) := Inv_key_MC(k4)//decode key
  //k5
  val w20 = Wire(UInt(32.W))
  val w21 = Wire(UInt(32.W))
  val w22 = Wire(UInt(32.W))
  val w23 = Wire(UInt(32.W))
  val k5 = Wire(UInt(128.W))
  w20 := XOR32(G_func(k4(127,96),RC_table.rc(4)), k4(31,0))
  w21 := XOR32(w20, k4(63,32))
  w22 := XOR32(w21, k4(95,64))
  w23 := XOR32(w22, k4(127,96))
  k5 := Cat(w23,w22,w21,w20) //encode key
  io.key_out(5) := Inv_key_MC(k5)//decode key
  //k6
  val w24 = Wire(UInt(32.W))
  val w25 = Wire(UInt(32.W))
  val w26 = Wire(UInt(32.W))
  val w27 = Wire(UInt(32.W))
  val k6 = Wire(UInt(128.W))
  w24 := XOR32(G_func(k5(127,96),RC_table.rc(5)), k5(31,0))
  w25 := XOR32(w24, k5(63,32))
  w26 := XOR32(w25, k5(95,64))
  w27 := XOR32(w26, k5(127,96))
  k6 := Cat(w27,w26,w25,w24) //encode key
  io.key_out(6) := Inv_key_MC(k6)//decode key
  //k7
  val w28 = Wire(UInt(32.W))
  val w29 = Wire(UInt(32.W))
  val w30 = Wire(UInt(32.W))
  val w31 = Wire(UInt(32.W))
  val k7 = Wire(UInt(128.W))
  w28 := XOR32(G_func(k6(127,96),RC_table.rc(6)), k6(31,0))
  w29 := XOR32(w28, k6(63,32))
  w30 := XOR32(w29, k6(95,64))
  w31 := XOR32(w30, k6(127,96))
  k7 := Cat(w31,w30,w29,w28) //encode key
  io.key_out(7) := Inv_key_MC(k7)//decode key
  //k8
  val w32 = Wire(UInt(32.W))
  val w33 = Wire(UInt(32.W))
  val w34 = Wire(UInt(32.W))
  val w35 = Wire(UInt(32.W))
  val k8 = Wire(UInt(128.W))
  w32 := XOR32(G_func(k7(127,96),RC_table.rc(7)), k7(31,0))
  w33 := XOR32(w32, k7(63,32))
  w34 := XOR32(w33, k7(95,64))
  w35 := XOR32(w34, k7(127,96))
  k8 := Cat(w35,w34,w33,w32) //encode key
  io.key_out(8) := Inv_key_MC(k8)//decode key
  //k9
  val w36 = Wire(UInt(32.W))
  val w37 = Wire(UInt(32.W))
  val w38 = Wire(UInt(32.W))
  val w39 = Wire(UInt(32.W))
  val k9 = Wire(UInt(128.W))
  w36 := XOR32(G_func(k8(127,96),RC_table.rc(8)), k8(31,0))
  w37 := XOR32(w36, k8(63,32))
  w38 := XOR32(w37, k8(95,64))
  w39 := XOR32(w38, k8(127,96))
  k9 := Cat(w39,w38,w37,w36) //encode key
  io.key_out(9) := Inv_key_MC(k9)//decode key
  //k10
  val w40 = Wire(UInt(32.W))
  val w41 = Wire(UInt(32.W))
  val w42 = Wire(UInt(32.W))
  val w43 = Wire(UInt(32.W))
  val k10 = Wire(UInt(128.W))
  w40 := XOR32(G_func(k9(127,96),RC_table.rc(9)), k9(31,0))
  w41 := XOR32(w40, k9(63,32))
  w42 := XOR32(w41, k9(95,64))
  w43 := XOR32(w42, k9(127,96))
  k10 := Cat(w43,w42,w41,w40) //encode key
  io.key_out(10) := k10
}

object inv_key_gen{
  def apply(key_in:UInt)={
    val m = Module(new inv_key_gen)
    m.io.key_in := key_in
    m.io.key_out
  }
}
