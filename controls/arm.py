import numpy
import matplotlib.pyplot as plt

class Arm:
	def __init__(self):
		# Stall Torque in N m
		self.stall_torque = 1.41
		# Stall Current in Amps
		self.stall_current = 89.0
		# Free Speed in RPM
		self.free_speed = 5840.0
		# Free Current in Amps
		self.free_current = 3.0
		# Moment of inertia of the arm in kg m^2
		self.mass = 15.0 * 0.454
		self.radius = 23.5 * 0.0254
		self.J = self.mass * self.radius
		# Resistance of the motor, divided by 2 to account for the 2 motors
		self.R = 12.0 / self.stall_current / 2
		# Motor velocity constant
		self.Kv = ((self.free_speed / 60.0 * 2.0 * numpy.pi) / (12.0 - self.R * self.free_current))
		# Torque constant
		self.Kt = self.stall_torque / self.stall_current
		# timestep
		self.dt = 0.01
		# gear ratio
		self.G = (16.0 / 54.0) * (1 / 90.0)
		
		self.A = -self.Kt / (self.Kv * self.R * self.J * self.G * self.G)
		self.B = self.Kt / (self.R * self.J * self.G)
		
		self.theta = 0.0
		self.w = 0.0
		self.a = 0.0
	
	def sim(self):
		self.a = self.A * self.w + self.B * 12.0
		self.a += -9.81 * 6.8 * 0.59 * numpy.sin(self.theta) / self.J
		self.w += self.a * self.dt
		self.theta += (self.w * self.dt + 0.5 * self.a * self.dt * self.dt)
		
x = Arm()

angles = []
times = []
t = 0.0

for time in range(0, 100):
	x.sim()
	angles.append(x.theta * 180.0 / 3.14)
	times.append(t)
	t += x.dt
	
plt.plot(times, angles)
plt.show()