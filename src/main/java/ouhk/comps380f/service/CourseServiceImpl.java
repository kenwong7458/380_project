package ouhk.comps380f.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ouhk.comps380f.dao.CourseRepository;
import ouhk.comps380f.exception.AttachmentNotFound;
import ouhk.comps380f.exception.CourseNotFound;
import ouhk.comps380f.model.Attachment;
import ouhk.comps380f.model.Course;

@Service
public class CourseServiceImpl implements CourseService {

    @Resource
    private CourseRepository courseRepo;

//    @Resource
//    private AttachmentRepository attachmentRepo;

    @Override
    @Transactional
    public List<Course> getCourse() {
        return courseRepo.findAll();
    }

    @Override
    @Transactional
    public Course getCourse(long id) {
        return courseRepo.findOne(id);
    }

    @Override
    @Transactional(rollbackFor = CourseNotFound.class)
    public void delete(long id) throws CourseNotFound {
        Course deletedTicket = courseRepo.findOne(id);
        if (deletedTicket == null) {
            throw new CourseNotFound();
        }
        courseRepo.delete(deletedTicket);
    }

    @Override
    @Transactional(rollbackFor = AttachmentNotFound.class)
    public void deleteAttachment(long courseId, String name) throws AttachmentNotFound {
        Course course = courseRepo.findOne(courseId);
        for (Attachment attachment : course.getAttachments()) {
            if (attachment.getName().equals(name)) {
                course.deleteAttachment(attachment);
                courseRepo.save(course);
                return;
            }
        }
        throw new AttachmentNotFound();
    }

//    @Override
//    @Transactional
//    public long createCourse(String customerName, String subject,
//            String body, List<Attachment> attachments) throws IOException {
//        Ticket ticket = new Ticket();
//        ticket.setCustomerName(customerName);
//        ticket.setSubject(subject);
//        ticket.setBody(body);
//
//        for (MultipartFile filePart : attachments) {
//            Attachment attachment = new Attachment();
//            attachment.setName(filePart.getOriginalFilename());
//            attachment.setMimeContentType(filePart.getContentType());
//            attachment.setContents(filePart.getBytes());
//            attachment.setTicket(ticket);
//            if (attachment.getName() != null && attachment.getName().length() > 0
//                    && attachment.getContents() != null
//                    && attachment.getContents().length > 0) {
//                ticket.getAttachments().add(attachment);
//            }
//        }
//        Ticket savedTicket = ticketRepo.save(ticket);
//        return savedTicket.getId();
//    }

//    @Override
//    @Transactional(rollbackFor = TicketNotFound.class)
//    public void updateTicket(long id, String subject,
//            String body, List<MultipartFile> attachments)
//            throws IOException, TicketNotFound {
//        Ticket updatedTicket = ticketRepo.findOne(id);
//        if (updatedTicket == null) {
//            throw new TicketNotFound();
//        }
//
//        updatedTicket.setSubject(subject);
//        updatedTicket.setBody(body);
//
//        for (MultipartFile filePart : attachments) {
//            Attachment attachment = new Attachment();
//            attachment.setName(filePart.getOriginalFilename());
//            attachment.setMimeContentType(filePart.getContentType());
//            attachment.setContents(filePart.getBytes());
//            attachment.setTicket(updatedTicket);
//            if (attachment.getName() != null && attachment.getName().length() > 0
//                    && attachment.getContents() != null
//                    && attachment.getContents().length > 0) {
//                updatedTicket.getAttachments().add(attachment);
//            }
//        }
//        ticketRepo.save(updatedTicket);
//    }
}
