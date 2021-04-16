package AES
import chisel3._
import chisel3.util._
//todo 流水化，s表也换为计算？
class Inv_AES128 extends Module{
  val io = IO(new AES128_IO)
  //inv key gen
  val inv_key = Wire(Vec(11,UInt(128.W)))
  inv_key := inv_key_gen(io.key_in)
  //round10
  val r10 = Reg(UInt(128.W))
  val round10_data_out =Wire(UInt(128.W))
  when (reset.toBool()){
    r10 := 0.U(128.W)
  } otherwise {
    r10 := io.data_in
  }
  round10_data_out := XOR128(r10,inv_key(10))
  //round9
  val c9 = Wire(UInt(128.W))
  val r9 = Reg(UInt(128.W))
  val round9_data_out =Wire(UInt(128.W))
  c9 := Inv_BS_MC(Inv_ShiftRows(round10_data_out))
  when (reset.toBool()){
    r9 := 0.U(128.W)
  } otherwise {
    r9 := c9
  }
  round9_data_out := XOR128(r9,inv_key(9))
  //round8
  val c8 = Wire(UInt(128.W))
  val r8 = Reg(UInt(128.W))
  val round8_data_out =Wire(UInt(128.W))
  c8 := Inv_BS_MC(Inv_ShiftRows(round9_data_out))
  when (reset.toBool()){
    r8 := 0.U(128.W)
  } otherwise {
    r8 := c8
  }
  round8_data_out := XOR128(r8,inv_key(8))
  //round7
  val c7 = Wire(UInt(128.W))
  val r7 = Reg(UInt(128.W))
  val round7_data_out =Wire(UInt(128.W))
  c7 := Inv_BS_MC(Inv_ShiftRows(round8_data_out))
  when (reset.toBool()){
    r7 := 0.U(128.W)
  } otherwise {
    r7 := c7
  }
  round7_data_out := XOR128(r7,inv_key(7))
  //round6
  val c6 = Wire(UInt(128.W))
  val r6 = Reg(UInt(128.W))
  val round6_data_out =Wire(UInt(128.W))
  c6 := Inv_BS_MC(Inv_ShiftRows(round7_data_out))
  when (reset.toBool()){
    r6 := 0.U(128.W)
  } otherwise {
    r6 := c6
  }
  round6_data_out := XOR128(r6,inv_key(6))
  //round5
  val c5 = Wire(UInt(128.W))
  val r5 = Reg(UInt(128.W))
  val round5_data_out =Wire(UInt(128.W))
  c5 := Inv_BS_MC(Inv_ShiftRows(round6_data_out))
  when (reset.toBool()){
    r5 := 0.U(128.W)
  } otherwise {
    r5 := c5
  }
  round5_data_out := XOR128(r5,inv_key(5))
  //round4
  val c4 = Wire(UInt(128.W))
  val r4 = Reg(UInt(128.W))
  val round4_data_out =Wire(UInt(128.W))
  c4 := Inv_BS_MC(Inv_ShiftRows(round5_data_out))
  when (reset.toBool()){
    r4 := 0.U(128.W)
  } otherwise {
    r4 := c4
  }
  round4_data_out := XOR128(r4,inv_key(4))
  //round3
  val c3 = Wire(UInt(128.W))
  val r3 = Reg(UInt(128.W))
  val round3_data_out =Wire(UInt(128.W))
  c3 := Inv_BS_MC(Inv_ShiftRows(round4_data_out))
  when (reset.toBool()){
    r3 := 0.U(128.W)
  } otherwise {
    r3 := c3
  }
  round3_data_out := XOR128(r3,inv_key(3))
  //round2
  val c2 = Wire(UInt(128.W))
  val r2 = Reg(UInt(128.W))
  val round2_data_out =Wire(UInt(128.W))
  c2 := Inv_BS_MC(Inv_ShiftRows(round3_data_out))
  when (reset.toBool()){
    r2 := 0.U(128.W)
  } otherwise {
    r2 := c2
  }
  round2_data_out := XOR128(r2,inv_key(2))
  //round1
  val c1 = Wire(UInt(128.W))
  val r1 = Reg(UInt(128.W))
  val round1_data_out =Wire(UInt(128.W))
  c1 := Inv_BS_MC(Inv_ShiftRows(round2_data_out))
  when (reset.toBool()){
    r1 := 0.U(128.W)
  } otherwise {
    r1 := c1
  }
  round1_data_out := XOR128(r1,inv_key(1))
  //round0
  val c0 = Wire(UInt(128.W))
  val r0 = Reg(UInt(128.W))
  val round0_data_out =Wire(UInt(128.W))
  c0 := Inv_BS(Inv_ShiftRows(round1_data_out))
  when (reset.toBool()){
    r0 := 0.U(128.W)
  } otherwise {
    r0 := c0
  }
  round0_data_out := XOR128(r0,inv_key(0))
  //output
  io.data_out := round0_data_out
}
