package AES

import chisel3._
import chisel3.util._
//todo 把s表全部换为计算 ,细粒度流水化
class AES128 extends Module{
  val io = IO(new AES128_IO)
  //round0
  val data_r0 = Reg(UInt(128.W))
  val key_r0 = Reg(UInt(128.W))
  val round0_data_out = Wire(UInt(128.W))
  when(reset.toBool()){
    data_r0 := 0.U(128.W)
    key_r0 := 0.U(128.W)
  } .otherwise {
    data_r0 := io.data_in //first round is different
    key_r0 := io.key_in
  }
  round0_data_out := XOR128(data_r0,key_r0)
  //round1
  val data_r1 = Reg(UInt(128.W))
  val key_r1 = Reg(UInt(128.W))
  val w4 = Wire(UInt(32.W))
  val w5 = Wire(UInt(32.W))
  val w6 = Wire(UInt(32.W))
  val w7 = Wire(UInt(32.W))
  val data_c1 = Wire(UInt(128.W))
  val round1_data_out = Wire(UInt(128.W))
  w4 := XOR32(G_func(key_r0(127,96),RC_table.rc(0)), key_r0(31,0))
  w5 := XOR32(w4, key_r0(63,32))
  w6 := XOR32(w5, key_r0(95,64))
  w7 := XOR32(w6, key_r0(127,96))
  data_c1 := BS_MC(ShiftRows(round0_data_out))
  when(reset.toBool()){
    data_r1 := 0.U(128.W)
    key_r1 := 0.U(128.W)
  } .otherwise {
    data_r1 := data_c1
    key_r1 := Cat(w7,w6,w5,w4)
  }
  round1_data_out := XOR128(data_r1,key_r1)
  //round2
  val data_r2 = Reg(UInt(128.W))
  val key_r2 = Reg(UInt(128.W))
  val w8 = Wire(UInt(32.W))
  val w9 = Wire(UInt(32.W))
  val w10 = Wire(UInt(32.W))
  val w11 = Wire(UInt(32.W))
  val data_c2 = Wire(UInt(128.W))
  val round2_data_out = Wire(UInt(128.W))
  w8 := XOR32(G_func(key_r1(127,96),RC_table.rc(1)), key_r1(31,0))
  w9 := XOR32(w8, key_r1(63,32))
  w10 := XOR32(w9, key_r1(95,64))
  w11 := XOR32(w10, key_r1(127,96))
  data_c2 := BS_MC(ShiftRows(round1_data_out))
  when(reset.toBool()){
    data_r2 := 0.U(128.W)
    key_r2 := 0.U(128.W)
  } .otherwise {
    data_r2 := data_c2
    key_r2 := Cat(w11,w10,w9,w8)
  }
  round2_data_out := XOR128(data_r2,key_r2)
  //round3
  val data_r3 = Reg(UInt(128.W))
  val key_r3 = Reg(UInt(128.W))
  val w12 = Wire(UInt(32.W))
  val w13 = Wire(UInt(32.W))
  val w14 = Wire(UInt(32.W))
  val w15 = Wire(UInt(32.W))
  val data_c3 = Wire(UInt(128.W))
  val round3_data_out = Wire(UInt(128.W))
  w12 := XOR32(G_func(key_r2(127,96),RC_table.rc(2)), key_r2(31,0))
  w13 := XOR32(w12, key_r2(63,32))
  w14 := XOR32(w13, key_r2(95,64))
  w15 := XOR32(w14, key_r2(127,96))
  data_c3 := BS_MC(ShiftRows(round2_data_out))
  when(reset.toBool()){
    data_r3 := 0.U(128.W)
    key_r3 := 0.U(128.W)
  } .otherwise {
    data_r3 := data_c3
    key_r3 := Cat(w15,w14,w13,w12)
  }
  round3_data_out := XOR128(data_r3,key_r3)
  //round4
  val data_r4 = Reg(UInt(128.W))
  val key_r4 = Reg(UInt(128.W))
  val w16 = Wire(UInt(32.W))
  val w17 = Wire(UInt(32.W))
  val w18 = Wire(UInt(32.W))
  val w19 = Wire(UInt(32.W))
  val data_c4 = Wire(UInt(128.W))
  val round4_data_out = Wire(UInt(128.W))
  w16 := XOR32(G_func(key_r3(127,96),RC_table.rc(3)), key_r3(31,0))
  w17 := XOR32(w16, key_r3(63,32))
  w18 := XOR32(w17, key_r3(95,64))
  w19 := XOR32(w18, key_r3(127,96))
  data_c4 := BS_MC(ShiftRows(round3_data_out))
  when(reset.toBool()){
    data_r4 := 0.U(128.W)
    key_r4 := 0.U(128.W)
  } .otherwise {
    data_r4 := data_c4
    key_r4 := Cat(w19,w18,w17,w16)
  }
  round4_data_out := XOR128(data_r4,key_r4)
  //round5
  val data_r5 = Reg(UInt(128.W))
  val key_r5 = Reg(UInt(128.W))
  val w20 = Wire(UInt(32.W))
  val w21 = Wire(UInt(32.W))
  val w22 = Wire(UInt(32.W))
  val w23 = Wire(UInt(32.W))
  val data_c5 = Wire(UInt(128.W))
  val round5_data_out = Wire(UInt(128.W))
  w20 := XOR32(G_func(key_r4(127,96),RC_table.rc(4)), key_r4(31,0))
  w21 := XOR32(w20, key_r4(63,32))
  w22 := XOR32(w21, key_r4(95,64))
  w23 := XOR32(w22, key_r4(127,96))
  data_c5 := BS_MC(ShiftRows(round4_data_out))
  when(reset.toBool()){
    data_r5 := 0.U(128.W)
    key_r5 := 0.U(128.W)
  } .otherwise {
    data_r5 := data_c5
    key_r5 := Cat(w23,w22,w21,w20)
  }
  round5_data_out := XOR128(data_r5,key_r5)
  //round6
  val data_r6 = Reg(UInt(128.W))
  val key_r6 = Reg(UInt(128.W))
  val w24 = Wire(UInt(32.W))
  val w25 = Wire(UInt(32.W))
  val w26 = Wire(UInt(32.W))
  val w27 = Wire(UInt(32.W))
  val data_c6 = Wire(UInt(128.W))
  val round6_data_out = Wire(UInt(128.W))
  w24 := XOR32(G_func(key_r5(127,96),RC_table.rc(5)), key_r5(31,0))
  w25 := XOR32(w24, key_r5(63,32))
  w26 := XOR32(w25, key_r5(95,64))
  w27 := XOR32(w26, key_r5(127,96))
  data_c6 := BS_MC(ShiftRows(round5_data_out))
  when(reset.toBool()){
    data_r6 := 0.U(128.W)
    key_r6 := 0.U(128.W)
  } .otherwise {
    data_r6 := data_c6
    key_r6 := Cat(w27,w26,w25,w24)
  }
  round6_data_out := XOR128(data_r6,key_r6)
  //round7
  val data_r7 = Reg(UInt(128.W))
  val key_r7 = Reg(UInt(128.W))
  val w28 = Wire(UInt(32.W))
  val w29 = Wire(UInt(32.W))
  val w30 = Wire(UInt(32.W))
  val w31 = Wire(UInt(32.W))
  val data_c7 = Wire(UInt(128.W))
  val round7_data_out = Wire(UInt(128.W))
  w28 := XOR32(G_func(key_r6(127,96),RC_table.rc(6)), key_r6(31,0))
  w29 := XOR32(w28, key_r6(63,32))
  w30 := XOR32(w29, key_r6(95,64))
  w31 := XOR32(w30, key_r6(127,96))
  data_c7 := BS_MC(ShiftRows(round6_data_out))
  when(reset.toBool()){
    data_r7 := 0.U(128.W)
    key_r7 := 0.U(128.W)
  } .otherwise {
    data_r7 := data_c7
    key_r7 := Cat(w31,w30,w29,w28)
  }
  round7_data_out := XOR128(data_r7,key_r7)
  //round8
  val data_r8 = Reg(UInt(128.W))
  val key_r8 = Reg(UInt(128.W))
  val w32 = Wire(UInt(32.W))
  val w33 = Wire(UInt(32.W))
  val w34 = Wire(UInt(32.W))
  val w35 = Wire(UInt(32.W))
  val data_c8 = Wire(UInt(128.W))
  val round8_data_out = Wire(UInt(128.W))
  w32 := XOR32(G_func(key_r7(127,96),RC_table.rc(7)), key_r7(31,0))
  w33 := XOR32(w32, key_r7(63,32))
  w34 := XOR32(w33, key_r7(95,64))
  w35 := XOR32(w34, key_r7(127,96))
  data_c8 := BS_MC(ShiftRows(round7_data_out))
  when(reset.toBool()){
    data_r8 := 0.U(128.W)
    key_r8 := 0.U(128.W)
  } .otherwise {
    data_r8 := data_c8
    key_r8 := Cat(w35,w34,w33,w32)
  }
  round8_data_out := XOR128(data_r8,key_r8)
  //round9
  val data_r9 = Reg(UInt(128.W))
  val key_r9 = Reg(UInt(128.W))
  val w36 = Wire(UInt(32.W))
  val w37 = Wire(UInt(32.W))
  val w38 = Wire(UInt(32.W))
  val w39 = Wire(UInt(32.W))
  val data_c9 = Wire(UInt(128.W))
  val round9_data_out = Wire(UInt(128.W))
  w36 := XOR32(G_func(key_r8(127,96),RC_table.rc(8)), key_r8(31,0))
  w37 := XOR32(w36, key_r8(63,32))
  w38 := XOR32(w37, key_r8(95,64))
  w39 := XOR32(w38, key_r8(127,96))
  data_c9 := BS_MC(ShiftRows(round8_data_out))
  when(reset.toBool()){
    data_r9 := 0.U(128.W)
    key_r9 := 0.U(128.W)
  } .otherwise {
    data_r9 := data_c9
    key_r9 := Cat(w39,w38,w37,w36)
  }
  round9_data_out := XOR128(data_r9,key_r9)
  //round10
  val data_r10 = Reg(UInt(128.W))
  val key_r10 = Reg(UInt(128.W))
  val w40 = Wire(UInt(32.W))
  val w41 = Wire(UInt(32.W))
  val w42 = Wire(UInt(32.W))
  val w43 = Wire(UInt(32.W))
  val data_c10 = Wire(UInt(128.W))
  val round10_data_out = Wire(UInt(128.W))
  w40 := XOR32(G_func(key_r9(127,96),RC_table.rc(9)), key_r9(31,0))
  w41 := XOR32(w40, key_r9(63,32))
  w42 := XOR32(w41, key_r9(95,64))
  w43 := XOR32(w42, key_r9(127,96))
  data_c10 := BS(ShiftRows(round9_data_out)) //last round is different
  when(reset.toBool()){
    data_r10 := 0.U(128.W)
    key_r10 := 0.U(128.W)
  } .otherwise {
    data_r10 := data_c10
    key_r10 := Cat(w43,w42,w41,w40)
  }
  round10_data_out := XOR128(data_r10,key_r10)
  // output
  io.data_out := round10_data_out
}

object AES128{
  def apply(data_in: UInt, key_in:UInt )={
    val m = Module(new AES128)
    m.io.data_in := data_in
    m.io.key_in := key_in
    m.io.data_out
  }
}
